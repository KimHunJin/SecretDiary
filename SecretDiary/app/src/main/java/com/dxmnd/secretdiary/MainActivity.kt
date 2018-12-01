package com.dxmnd.secretdiary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.dxmnd.secretdiary.utils.decrypt
import com.dxmnd.secretdiary.utils.encrypt
import com.dxmnd.secretdiary.utils.log
import com.dxmnd.secretdiary.utils.md5
import kotlinx.android.synthetic.main.activity_main.*


// 키로 키 암호화 --> 저장
// 암호화 된 키로 글 암호화

// 키로 키 암호화
// 암호화 된 키로 글 복호화

// 시크릿 키로 검색

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val key = "Secret" // 사용자가 입력한 비밀키 (PK가 된다)

        val hashKey = md5(key)

        var hash = ""
        for(i in 0 until  hashKey.length) {
            if(hashKey[i] != '-') {
                hash += hashKey[i]
            }
        }

        log(hash)

        val keyBytes = hash.toByteArray(charset("UTF-8"))
        val ivBytes = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00)

        btn_encryption.setOnClickListener {
            val enText = encrypt(ivBytes, keyBytes, txt_main.text.toString().toByteArray())
            val text = Base64.encodeToString(enText, Base64.DEFAULT)
            txt_main.text = text
        }
        btn_decryption.setOnClickListener {
            val deText = decrypt(ivBytes, keyBytes, Base64.decode(txt_main.text.toString().toByteArray(),Base64.DEFAULT))
            val text = String(deText)
            txt_main.text = text
        }
    }
}
