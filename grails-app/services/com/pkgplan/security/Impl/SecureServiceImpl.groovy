package com.pkgplan.security.Impl

import com.pkgplan.dream.Server
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import com.pkgplan.security.SecureService
import org.springframework.stereotype.Service

@Service("secureService")
class SecureServiceImpl implements SecureService{

    def generateSecFile(String hostname) {
        def server = Server.findByHostname(hostname)
        if (!server) {
            return null
        }
        def fileString = "# client\tserver\tsecret\tIP addresses";
        def protocol = "l2tpd"
        def now = new Date()
        for (user in server.users) {
            if (user.dateExpired > now) {
                fileString += "\n${user.username}\t${protocol}\t${encodePasswordForVpn(user.password)}\t*"
            }
        }

        def fileContent = new ByteArrayInputStream(fileString.getBytes("UTF-8"))
    }

    /**
     * this encrypt method encrypt the password again and only get first 8 chars for user vpn connection
     * we can also use this method to get the vpn connection password, and show it in the user profile page.
     * @param password
     */
    String encodePasswordForVpn(String password) {
        if (password) {
            return password.encodeAsSHA1().substring(0,8)
        }
        return password;
    }

    static def getLoginUrl() {

        return SpringSecurityUtils.securityConfig.apf.filterProcessesUrl
    }

    static def getRememberMeParam() {
        return SpringSecurityUtils.securityConfig.rememberMe.parameter
    }
}
