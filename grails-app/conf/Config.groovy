// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = com.pkgplan // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'com.pkgplan.auth.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'com.pkgplan.auth.UserRole'
grails.plugins.springsecurity.authority.className = 'com.pkgplan.auth.Role'


grails {
    mail {
        host = "smtp.gmail.com"
        port = 465
        username = "master@pkgplan.com"
        password = ""
        props = ["mail.smtp.auth":"true",
                "mail.smtp.socketFactory.port":"465",
                "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
                "mail.smtp.socketFactory.fallback":"false"]
    }
}

// sprint security limit
grails.plugins.springsecurity.controllerAnnotations.staticRules = [
        '/aclClass/**': ['ROLE_ADMIN'],
        '/aclSid/**': ['ROLE_ADMIN'],
        '/aclObjectIdentity/**': ['ROLE_ADMIN'],
        '/aclEntry/**': ['ROLE_ADMIN'],
        '/persistentLogin/**': ['ROLE_ADMIN'],
        '/requestmap/**': ['ROLE_ADMIN'],
        '/securityInfo/**': ['ROLE_ADMIN'],
        '/registrationCode/**': ['ROLE_ADMIN'],
        '/role/**': ['ROLE_ADMIN'],
        '/console/**': ['ROLE_ADMIN'],
        '/register/**': ['IS_AUTHENTICATED_ANONYMOUSLY']
]

// This doesn't mean not encode the password, but not to do it 2 times, because security core is doing it
// security ui doesn't need to do
grails.plugins.springsecurity.ui.encodePassword = false
// password strength
grails.plugins.springsecurity.ui.password.minLength=6
grails.plugins.springsecurity.ui.password.maxLength=12
grails.plugins.springsecurity.ui.password.validationRegex='^.*(?=.*\\d)(?=.*[a-zA-Z]).*$'

// for heroku
grails.plugin.databasesession.enabled = false

api {
    serviceKey = 'abcdefg'
}

// load config from
grails.config.locations =
    ["file:${userHome}/.grails/${appName}-config.groovy",
            "file:${userHome}/.grails/${appName}-dataSource.groovy"]