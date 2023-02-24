package nei.demo.modalinsuspend.utils

import android.content.Context
import android.widget.Toast

fun Context.toast(msg: String): Toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)

fun Context.showToast(msg: String) {
    toast(msg).show()
}