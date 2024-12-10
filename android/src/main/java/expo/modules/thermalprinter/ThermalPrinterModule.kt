package expo.modules.thermalprinter

import android.Manifest
import android.os.Build
import com.dantsu.escposprinter.EscPosPrinter
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections
import expo.modules.interfaces.permissions.PermissionsResponseListener
import expo.modules.kotlin.Promise
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class ThermalPrinterModule : Module() {
  private val bluetoothPermissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
    arrayOf(
      Manifest.permission.BLUETOOTH,
      Manifest.permission.BLUETOOTH_ADMIN,
      Manifest.permission.BLUETOOTH_SCAN, // Necessário para escanear dispositivos Bluetooth
      Manifest.permission.BLUETOOTH_CONNECT // Para Android 12+
    )
  } else {
    arrayOf(
      Manifest.permission.BLUETOOTH,
      Manifest.permission.BLUETOOTH_ADMIN
    )
  }

  override fun definition() = ModuleDefinition {
    Name("ThermalPrinter")

    AsyncFunction("requestBluetoothPermissions") { promise: Promise ->
      appContext.permissions?.askForPermissions(PermissionsResponseListener{ result ->
        promise.resolve(result.map { mapOf( it.key to mapOf(
          "status" to it.value.status.toString(),
          "canAskAgain" to it.value.canAskAgain
        ))})
      }, *bluetoothPermissions)
    }

    Function("print") { text: String ->
      val printer = EscPosPrinter(BluetoothPrintersConnections.selectFirstPaired(), 203, 48f, 32)
      printer
        .printFormattedText(text.trimIndent())
    }
  }
}
