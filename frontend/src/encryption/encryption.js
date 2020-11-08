const {generateKeyPair} = require('crypto')
import CryptoJS from 'crypto-js'

// eslint-disable-next-line no-unused-vars
function generateKeys(pwd){
    let keys = {
        privateKey: "",
        publicKey: ""
    }
    generateKeyPair('rsa', {
        modulusLength: 2048,
        publicExponent: 65537,
        publicKeyEncoding:{
            type: "pkcs1",
            format: "der"
        },
        privateKeyEncoding:{
            type: "pkcs8",
            format: "der",
            cipher: "aes-192-cbc",
            passphrase: pwd
        }
    },(err, publicKey, privateKey)=>{
        if(!err){
            keys['privateKey'] = privateKey
            keys['publicKey'] = publicKey
            return keys
        }
        else{
            console.log("error generating keys")
        }
    })
}

// eslint-disable-next-line no-unused-vars
function encrypt(msg, publicKey){
    return CryptoJS.AES.encrypt(msg, publicKey)
}

// eslint-disable-next-line no-unused-vars
function decrypt(msg, privateKey){
    return CryptoJS.AES.decrypt(msg, privateKey)
}





