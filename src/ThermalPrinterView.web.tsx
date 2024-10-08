import * as React from 'react';

import { ThermalPrinterViewProps } from './ThermalPrinter.types';

export default function ThermalPrinterView(props: ThermalPrinterViewProps) {
  return (
    <div>
      <span>{props.name}</span>
    </div>
  );
}
