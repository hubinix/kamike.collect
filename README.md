# kamike.collect
Another Simple Crawler
又一个网络爬虫，可以支持代理服务器的翻墙爬取。
1.数据存在mysql当中。
2.使用时，先修改web-inf/config.ini的数据链接相关信息，主要是数据库名和用户名和密码
3.然后访问http://127.0.0.1/fetch/install 链接，自动创建数据库表
4.修改src\java\cn\exinhua\fetch中的RestServlet.java文件：

       FetchInst.getInstance().running=true;
       Fetch fetch = new Fetch();
       
        fetch.setUrl("http://www.washingtonpost.com/");
        
        fetch.setDepth(3);
        
        RegexRule regexRule = new RegexRule();
        
        regexRule.addNegative(".*#.*");
        
        regexRule.addNegative(".*png.*");
        
        regexRule.addNegative(".*jpg.*");
        
        regexRule.addNegative(".*gif.*");
        
        regexRule.addNegative(".*js.*");
        
        regexRule.addNegative(".*css.*");
        
        regexRule.addPositive(".*php.*");
        
        regexRule.addPositive(".*html.*");
        
        regexRule.addPositive(".*htm.*");
        
        Fetcher fetcher = new Fetcher(fetch);
        
        fetcher.setProxyAuth(true);
        
        fetcher.setRegexRule(regexRule);
        
        List<Fetcher> fetchers = new ArrayList<>();

        
        fetchers.add(fetcher);
        FetchUtils.start(fetchers);
        
        
        将其配置为需要的参数，然后访问http://127.0.0.1/fetch/fetch启动爬取
5.访问http://127.0.0.1/fetch/suspend可以停止爬取
