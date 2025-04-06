https://drive.google.com/file/d/1qKvDge2H6DABQz4OtWNP-drxo9IKwN4Y/view?usp=sharing  video de muestra

Prerrequisitos
Android Studio (versión Flamingo o superior recomendada)
JDK 17 o superior
Dispositivo Android o emulador configurado

Cómo ejecutar el proyecto
Clona el repositorio:
git clone https://github.com/tu-usuario/BMICalculator.git
cd BMICalculator
Abre el proyecto en Android Studio:

Inicia Android Studio
Selecciona "Open" y navega hasta la carpeta del proyecto
Haz clic en "OK"

Configura el dispositivo:
Conecta un dispositivo Android con modo desarrollador activado O
Configura un emulador desde AVD Manager (Tools > Device Manager)

Sincroniza el proyecto:
Android Studio descargará automáticamente las dependencias
Si no lo hace, haz clic en "Sync Project with Gradle Files" (icono de elefante)

Ejecuta la aplicación:
Selecciona tu dispositivo/emulador en la barra superior
Haz clic en el botón "Run" (triángulo verde) o presiona Shift+F10


//caso totalmente extremo donde halla q recurrir a alternativas 
./gradlew clean
./gradlew build
Ejecuta desde terminal:
./gradlew installDebug
adb shell am start -n com.example.bmicalculator/.MainActivity
