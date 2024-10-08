import { requireNativeViewManager } from 'expo-modules-core';
import * as React from 'react';

import { ThermalPrinterViewProps } from './ThermalPrinter.types';

const NativeView: React.ComponentType<ThermalPrinterViewProps> =
  requireNativeViewManager('ThermalPrinter');

export default function ThermalPrinterView(props: ThermalPrinterViewProps) {
  return <NativeView {...props} />;
}
