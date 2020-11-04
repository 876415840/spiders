package com.example.demo.service.impl

import com.example.demo.entity.JobDetail
import com.example.demo.mapper.JobDetailMapper
import com.example.demo.service.JobService
import com.example.demo.util.SpiderUtils
import org.apache.commons.lang3.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Character.UnicodeBlock
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Boss直聘-工作相关接口实现
 *
 * @author stephen
 * @date 2020/10/28 11:44 上午
 */
@Service
class BossZhiPinService : JobService {

    private val logger: Logger = LoggerFactory.getLogger(BossZhiPinService::class.java)

    val baseUrl = "https://www.zhipin.com"

    @Autowired
    private
    lateinit var jobDetailMapper: JobDetailMapper

    /**
     * 获取技术名词
     *
     * @author stephen
     * @date 2020/10/29 5:06 下午
     */
    fun getTechTerm(chars: CharArray): MutableList<String> {
        var list:  MutableList<String> = ArrayList()
        var save = false
        var sb = StringBuilder()
        for (i in chars.indices) {
            var char = chars[i]
            if (char >= 'a' && char <= 'z') {
                save = true
            } else if (char >= 'A' && char <= 'Z') {
                char += 32
                save = true
            } else if (isChinese(char) || sepa(char)) {
                save = false
            }
            if (save && char != ' ') {
                sb.append(char)
            } else if (sb.isNotBlank()) {
                if (sb.length < 32) {
                    list.add(sb.toString())
                }
                sb.clear()
            }
        }
        return list
    }

    override fun spiderData() {
//        jobTabs()

        jobDetail()
    }

    fun handleDetails(detailUrl: String) : MutableList<String> {
        var result: MutableList<String> = ArrayList()
        var html = SpiderUtils.getHtmlPageResponse(StringUtils.join(baseUrl, detailUrl))
        var htmls = SpiderUtils.getHtml(html, listOf("job-box", "job-detail", "detail-content", "job-sec", "text"))
        if (htmls.isEmpty()) {
            return result
        }
        var details = htmls[0].split("<br>")
        for (detail in details) {
            var techTerms = getTechTerm(detail.toCharArray())
            for (techTerm in techTerms) {
                result.add(techTerm)
            }
        }
        return result
    }

    /**
     * 招聘要求明细
     *
     * @author stephen
     * @date 2020/10/29 4:13 下午
     */
    fun jobDetail() {
        var html = SpiderUtils.getHtmlPageResponse(StringUtils.join(baseUrl, "/c101010100-p100101/"))
        var result = handlePageDetail(html)
        nextPageDetail(html, result)
        var map = toCountMap(result)
        logger.info("================================技术要求明细统计====================================={}", map.size)
        var now = Date()
        for (entry in map) {
            logger.info("{} -> {}", entry.key, entry.value)
            var jobDetail = JobDetail()
            jobDetail.techTerm = entry.key
            jobDetail.count = entry.value
            jobDetail.tag = "java"
            jobDetail.createTime = now
            jobDetail.updateTime = now
            jobDetailMapper.save(jobDetail)
        }
    }

    fun handlePageDetail(html: String) : MutableList<String> {
        var detailUrls = SpiderUtils.getAttr(html, listOf("job-list", "job-primary", "primary-wrapper", "primary-box"), "href")

        var details: MutableList<String>  = ArrayList()
        for (detailUrl in detailUrls) {
            TimeUnit.SECONDS.sleep(Random(10).nextLong())
            details.addAll(handleDetails(detailUrl))
        }
        return details
    }

    fun nextPageDetail(html: String, details: MutableList<String>) {
        var newHtml: String = nextPage(html) ?: return
        details.addAll(handlePageDetail(newHtml))
        nextPageDetail(newHtml, details)
    }

    fun toCountMap(list: MutableList<String>) : MutableMap<String, Int> {
        var map: MutableMap<String, Int> = HashMap()
        for (tab in list) {
            var count = map.get(tab)
            if (count == null) {
                map.put(tab, 1)
            } else {
                map.put(tab, count + 1)
            }
        }
        return map
    }

    /**
     * 招聘要求标签
     *
     * @author stephen
     * @date 2020/10/29 3:09 下午
     */
    fun jobTabs() {
        var html = SpiderUtils.getHtmlPageResponse(StringUtils.join(baseUrl, "/c101010100-p100101/"))
        var tabs = SpiderUtils.getText(html, listOf("job-list", "job-primary", "info-append", "tag-item"))
        nextPageTabs(html, tabs)
        var map = toCountMap(tabs)
        logger.info("================================技术标签统计====================================={}", map.size)
        map.forEach { (k: String, v: Int) -> println(StringUtils.join(k, ",", v)) }
    }

    fun nextPageTabs(html: String, tabs: MutableList<String>) {
        var newHtml: String = nextPage(html) ?: return
        var nextTabs = SpiderUtils.getText(newHtml, listOf("job-list", "job-primary", "info-append", "tag-item"))
        tabs.addAll(nextTabs)
        nextPageTabs(newHtml, tabs)
    }

    fun nextPage(html: String) : String? {
        var urls = SpiderUtils.getAttr(html, listOf("job-list", "page", "next"), "href")
        if (urls.isEmpty() || StringUtils.isBlank(urls[0]) || urls[0].equals("javascript:;")) {
            return null
        }
        var url = StringUtils.join(baseUrl, urls[0])
        logger.info("-----next---->{}", url)
        return SpiderUtils.getHtmlPageResponse(url)
    }

    private fun isChinese(c: Char): Boolean {
        val ub = UnicodeBlock.of(c)
        return ub === UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub === UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub === UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub === UnicodeBlock.GENERAL_PUNCTUATION || ub === UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub === UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
    }
    private fun sepa(c: Char): Boolean {
        return when (c) {
            ',', ';'
            -> true
            else -> false
        }
    }
}