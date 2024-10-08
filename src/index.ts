import { NativeModulesProxy, EventEmitter, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to ThermalPrinter.web.ts
// and on native platforms to ThermalPrinter.ts
import ThermalPrinterModule from './ThermalPrinterModule';
import ThermalPrinterView from './ThermalPrinterView';
import { ChangeEventPayload, ThermalPrinterViewProps } from './ThermalPrinter.types';

// Get the native constant value.
export const PI = ThermalPrinterModule.PI;

export function hello(): string {
  return ThermalPrinterModule.hello();
}

export async function setValueAsync(value: string) {
  return await ThermalPrinterModule.setValueAsync(value);
}

const emitter = new EventEmitter(ThermalPrinterModule ?? NativeModulesProxy.ThermalPrinter);

export function addChangeListener(listener: (event: ChangeEventPayload) => void): Subscription {
  return emitter.addListener<ChangeEventPayload>('onChange', listener);
}

export { ThermalPrinterView, ThermalPrinterViewProps, ChangeEventPayload };
