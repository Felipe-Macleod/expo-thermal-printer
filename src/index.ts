import ThermalPrinterModule from "./ThermalPrinterModule";

export function hello(): string {
  return ThermalPrinterModule.hello();
}

export function print(text: string) {
  return ThermalPrinterModule.print(text);
}

export async function requestBluetoothPermissions() {
  return ThermalPrinterModule.requestBluetoothPermissions();
}
