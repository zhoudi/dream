package com.pkgplan

import com.pkgplan.dream.Server

class SecureService {

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
    def encodePasswordForVpn(String password) {
        if (password) {
            return password.encodeAsSHA1().substring(0,8)
        }
        return password;
    }
}
