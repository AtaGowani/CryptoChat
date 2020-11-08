
let CryptoJS = require('crypto-js')
import * as crypt from "cryptico"

export default class enc {

    static generateKeys(pwd) {
        console.log("Hello")
        let privateKey = crypt.generateRSAKey(pwd, 4096)
        localStorage.setItem("key", JSON.stringify(privateKey))
        var key  = JSON.parse(localStorage.getItem("key"))
        console.log(key)
        console.log("saved")
        return crypt.publicKeyString(privateKey)
    }
   static encrypt(msg, publicKey) {
        return CryptoJS.AES.encrypt(msg, publicKey)
    }

    static decrypt(msg, privateKey) {
        return CryptoJS.AES.decrypt(msg, privateKey)
    }
}

