package nei.demo.modalinsuspend

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import nei.demo.modalinsuspend.data.actions
import nei.demo.modalinsuspend.databinding.ActivityMainBinding
import nei.demo.modalinsuspend.ui.dialog.showActionBottomSheetDialog
import nei.demo.modalinsuspend.ui.dialog_fragment.ActionBottomSheetDialogFragment.Companion.showActionBottomSheetDialogFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.actionBottomSheetDialogBtn.launchOnClick {
            val actionSelected =
                showActionBottomSheetDialog(this@MainActivity, actions)
            toast("Action selected ${actionSelected?.type ?: "none"}")
        }

        binding.actionBottomSheetDialogFragmentBtn.launchOnClick {
            val actionSelected =
                showActionBottomSheetDialogFragment(this@MainActivity, actions)
            toast("Action selected ${actionSelected?.type ?: "none"}")
        }
    }

    private inline fun View.launchOnClick(crossinline block: suspend CoroutineScope.(View) -> Unit) {
        setOnClickListener {
            lifecycleScope.launchWhenCreated { block.invoke(this, it) }
        }
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}