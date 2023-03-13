package nei.demo.jsontotextview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nei.demo.jsontotextview.R
import nei.demo.jsontotextview.databinding.ActivityMainBinding
import nei.demo.jsontotextview.model.Subtitle
import nei.demo.jsontotextview.model.getSpanBuilder
import nei.demo.jsontotextview.rawToModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val (subtitleList: ArrayList<Subtitle>) = rawToModel(R.raw.subtitle) ?: return
        val labels = subtitleList.mapNotNull { it.props?.label }

        // without style
        val text = labels.joinToString(separator = "")
        binding.withoutStyleTextView.text = text

        // with style
        val spanBuilder = subtitleList.getSpanBuilder(resources = resources)
        binding.withStyleTextView.text = spanBuilder
        binding.withStyleEllipsizeTextView.text = spanBuilder
    }
}