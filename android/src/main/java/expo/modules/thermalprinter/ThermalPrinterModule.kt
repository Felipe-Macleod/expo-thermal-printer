package expo.modules.thermalprinter

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dantsu.escposprinter.EscPosPrinter
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections
import expo.modules.kotlin.exception.Exceptions
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition


class ThermalPrinterModule : Module() {
  val context: Context
    get() = appContext.reactContext ?: throw Exceptions.ReactContextLost()

  val activity: Activity
    get() = appContext.currentActivity ?: throw Exceptions.MissingActivity()

  private val bluetoothPermissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
    arrayOf(
      Manifest.permission.BLUETOOTH,
      Manifest.permission.BLUETOOTH_ADMIN,
      Manifest.permission.BLUETOOTH_SCAN, // Necessário para escanear dispositivos Bluetooth
      Manifest.permission.BLUETOOTH_CONNECT // Para Android 12+
    )
  } else {
    TODO("VERSION.SDK_INT < S")
  }

  companion object {
    private const val PERMISSION_REQUEST_CODE = 1001
  }

  override fun definition() = ModuleDefinition {
    Name("ThermalPrinter")

    Constants(
      "PI" to Math.PI
    )

    Events("onChange")

    Function("hello") {
      "Hello Marcos!"
    }

    Function("requestBluetoothPermissions") {
      appContext.permissions?.askForPermissions(expo.modules.interfaces.permissions.PermissionsResponseListener {  }, *bluetoothPermissions)
    }

    Function("print") { text: String ->
      val printer = EscPosPrinter(BluetoothPrintersConnections.selectFirstPaired(), 203, 48f, 32)
      printer
        .printFormattedText(text.trimIndent())
    }

    AsyncFunction("setValueAsync") { value: String ->
      sendEvent("onChange", mapOf(
        "value" to value
      ))
    }

    View(ThermalPrinterView::class) {
      // Defines a setter for the `name` prop.
      Prop("name") { view: ThermalPrinterView, prop: String ->
        println(prop)
      }
    }
  }
}
