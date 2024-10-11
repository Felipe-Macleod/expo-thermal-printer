import { Button, StyleSheet, Text, View } from "react-native";
import * as ThermalPrinter from "expo-thermal-printer";

const text = `
  [L]
  [C]<u><font size='big'>Macleod</font></u>
  [L]
  [C]================================
  [L]
  [L]Resolveu sem gastar usando:
  [C]<b>Kotlin</b>
  [L]
  [C]--------------------------------
`;

export default function App() {
  return (
    <View style={styles.container}>
      <View style={{ margin: 10 }}>
        <Button
          title="Request Permission"
          onPress={() =>
            ThermalPrinter.requestBluetoothPermissions().then(console.log)
          }
        />
      </View>
      <View style={{ margin: 10 }}>
        <Button title="Print" onPress={() => ThermalPrinter.print(text)} />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});
