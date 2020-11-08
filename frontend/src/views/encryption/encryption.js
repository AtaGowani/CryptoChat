const {generateKeyPair} = require('crypto')
import CryptoJS from 'crypto-js'

// eslint-disable-next-line no-unused-vars
export default class enc {
    static generateKeys(pwd) {
        let keys = {
            privateKey: "",
            publicKey: ""
        }
        generateKeyPair('rsa', {
            modulusLength: 2048,
            publicExponent: 65537,
            publicKeyEncoding: {
                type: "pkcs1",
                format: "der"
            },
            privateKeyEncoding: {
                type: "pkcs8",
                format: "der",
                cipher: "aes-192-cbc",
                passphrase: pwd
            }
        }, (err, publicKey, privateKey) => {
            if (!err) {
                keys['privateKey'] = privateKey
                let fs = require("fs")
                fs.writeFile("priv.txt", privateKey, function (err) {
                    if (err) throw err;
                    console.log("saved")
                })
                fs.close()
                keys['publicKey'] = publicKey
                return keys
            } else {
                console.log("error generating keys")
            }
        })
    }
   static encrypt(msg, publicKey) {
        return CryptoJS.AES.encrypt(msg, publicKey)
    }

    static decrypt(msg, privateKey) {
        return CryptoJS.AES.decrypt(msg, privateKey)
    }
}

