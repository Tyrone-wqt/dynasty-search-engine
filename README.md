# dynasty-search-engine

    Automatically exported from code.google.com/p/dynasty-search-engine

一个小型Java搜索引擎的实现，包含网络爬虫、预处理模块以及查询服务

项目为一个Java搜索引擎的实现(非Lucene)，根据搜索引擎的工作流程将实现分为三个模块，分别是：网络爬虫、预处理模块以及查询服务模块。系统首先使用网络爬虫爬取各个网页的源码并保存到本地，随后调用预处理模块将这些原始网页处理后进行分词并建立倒排索引以方便查询，最后搭建web服务器用JSP提供搜索服务。

网络爬虫采用多线程机制，提高网页的爬取效率；在预处理中，使用数据库存储网页的索引信息(由于系统规模不大)，分词器读取外部的词典对网页正文进行分词，用户可方便的编辑替换词典用于垂直搜索，倒排索引保存在内存中以方便查询；采用Tomcat搭建的web服务器提供JSP服务，是系统与用户交互的接口：接受用户输入并且返回查询结果。

PS:单纯的看代码可能对于了解搜索引擎的流程有些困难，对应的一个教程可以在http://www.ibm.com/developerworks/cn/views/java/libraryview.jsp?search_by=dySE 获得。
