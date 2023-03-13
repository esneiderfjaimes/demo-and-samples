package nei.demo.jsontotextview.model

import android.content.res.Resources
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import com.google.gson.annotations.SerializedName
import nei.demo.jsontotextview.R

data class Model(
    @SerializedName("subtitle") var subtitle: ArrayList<Subtitle> = arrayListOf()
)

data class Subtitle(
    @SerializedName("type") var type: String? = null,
    @SerializedName("props") var props: Props? = Props()
)

data class Props(
    @SerializedName("label") var label: String? = null,
    @SerializedName("type") var type: String? = null
)

fun ArrayList<Subtitle>.getSpanBuilder(resources: Resources): SpannableStringBuilder {
    val builder = SpannableStringBuilder()
    val textSizeS = resources.getDimension(R.dimen.text_size_s)
    val textSizeXS = resources.getDimension(R.dimen.text_size_xs)
    val textSizeM = resources.getDimension(R.dimen.text_size_m)

    forEach lit@{ subtitle ->
        if (subtitle.type == "typography") {
            val props = subtitle.props ?: return@lit

            val label = props.label ?: ""
            val type = props.type ?: ""

            val start = builder.length
            builder.append(label)
            val end = builder.length

            var color = Color.TRANSPARENT
            var textSize = 0f // Default

            when (type) {
                "titleXS" -> {
                    color = Color.RED
                    textSize = textSizeXS
                }
                "titleS" -> {
                    color = Color.BLUE
                    textSize = textSizeS
                }
                "titleM" -> {
                    color = Color.GREEN
                    textSize = textSizeM
                }
            }
            if (color != 0) {
                builder.setSpan(
                    ForegroundColorSpan(color), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            if (textSize > 0f) {
                builder.setSpan(
                    AbsoluteSizeSpan(textSize.toInt()), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
    }
    return builder
}