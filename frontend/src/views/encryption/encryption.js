import * as crypt from "cryptico"

export default class enc {

    static generateKeys(email) {
        let privateKey = crypt.generateRSAKey(email, 1024)
        localStorage.setItem("key", JSON.stringify(privateKey))
        console.log("saved")
        return crypt.publicKeyString(privateKey)
    }
   static encrypt(msg, publicKey) {
        return crypt.encrypt(msg, publicKey)
    }

    static decrypt(msg, privateKey) {
        return crypt.decrypt(msg, privateKey)
    }
}

