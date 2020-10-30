package com.example.demo.util

import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.gargoylesoftware.htmlunit.util.UrlUtils
import org.apache.commons.lang3.StringUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

/**
 * 爬虫工具
 *
 * @author stephen
 * @date 2020/10/28 5:57 下午
 */
class SpiderUtils {

    companion object {

        /**
         * 获取页面文档字串(等待异步JS执行)
         *
         * @param url 页面url
         * @author stephen
         * @date 2020/10/28 7:03 下午
         */
        fun getHtmlPageResponse(url: String): String {
            val webClient = WebClient(BrowserVersion.CHROME)
            //当JS执行出错的时候是否抛出异常
            webClient.options.setThrowExceptionOnScriptError(false)
            //当HTTP的状态非200时是否抛出异常
            webClient.options.setThrowExceptionOnFailingStatusCode(false)
            webClient.options.setActiveXNative(false)
            //是否启用CSS
            webClient.options.setCssEnabled(false)
            //很重要，启用JS
            webClient.options.setJavaScriptEnabled(true)
            //很重要，设置支持AJAX
            webClient.setAjaxController(NicelyResynchronizingAjaxController())
            //设置“浏览器”的请求超时时间
            webClient.options.setTimeout(30000)
            //设置JS执行的超时时间
            webClient.setJavaScriptTimeout(30000)

            var page: HtmlPage = webClient.getPage<HtmlPage>(url)
            webClient.waitForBackgroundJavaScript(30000);//该方法阻塞线程

            var result = page.asXml();
            webClient.close();

            return result;
        }

        /**
         * 获取元素对象
         *
         * @param html 页面HTML代码
         * @param classNames 逐级class,定位元素
         * @return 符合条件的元素列表
         * @author stephen
         * @date 2020/10/29 2:33 下午
         */
        fun getElements(html: String, classNames: List<String>): MutableList<Element> {
            //获取html文档
            var document = Jsoup.parse(html)
            var elements = document.getElementsByClass(classNames[0])
            var elementsArr : MutableList<Elements> = ArrayList()
            var result : MutableList<Element> = ArrayList()
            for (i in classNames.indices){
                if (i == 0) {
                    continue
                } else if (i == 1) {
                    for(element in elements) {
                        elementsArr.add(element.getElementsByClass(classNames[i])!!)
                    }
                }

                var elementsArr1 : MutableList<Elements>  = ArrayList()
                for(elements1 in elementsArr) {
                    for(element1 in elements1) {
                        elementsArr1.add(element1.getElementsByClass(classNames[i])!!)
                    }
                }
                elementsArr = elementsArr1

                if (i == classNames.size - 1) {
                    for(elements2 in elementsArr) {
                        for(element2 in elements2) {
                            result.add(element2)
                        }
                    }
                }

            }

            return result
        }

        /**
         * 获取元素text内容
         *
         * @param html 页面HTML代码
         * @param classNames 逐级class,定位元素
         * @return 符合条件的元素text内容列表
         * @author stephen
         * @date 2020/10/29 2:33 下午
         */
        fun getText(html: String, classNames: List<String>): MutableList<String> {
            var elements = getElements(html, classNames)
            var result : MutableList<String> = ArrayList()
            for (element in elements) {
                result.add(element.text())
            }
            return result
        }

        /**
         * 获取元素html内容
         *
         * @param html 页面HTML代码
         * @param classNames 逐级class,定位元素
         * @return 符合条件的元素html内容列表
         * @author stephen
         * @date 2020/10/29 2:33 下午
         */
        fun getHtml(html: String, classNames: List<String>): MutableList<String> {
            var elements = getElements(html, classNames)
            var result : MutableList<String> = ArrayList()
            for (element in elements) {
                result.add(element.html())
            }
            return result
        }

        /**
         * 获取元素属性值
         *
         * @param html 页面HTML代码
         * @param classNames 逐级class,定位元素
         * @param attr 属性名
         * @return 符合条件的元素属性值列表
         * @author stephen
         * @date 2020/10/29 2:33 下午
         */
        fun getAttr(html: String, classNames: List<String>, attr: String): MutableList<String> {
            var elements = getElements(html, classNames)
            var result : MutableList<String> = ArrayList()
            for (element in elements) {
                result.add(element.attr(attr)!!)
            }
            return result
        }



    }

}