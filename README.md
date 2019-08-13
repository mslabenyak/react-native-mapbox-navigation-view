
# react-native-mapbox-navigation-view

## Getting started

`$ npm install https://github.com/BaseOperations/react-native-mapbox-navigation-view.git`

### Mostly automatic installation

#### Add repositories into android/build.gradle
```
mavenCentral()
maven { url 'https://mapbox.bintray.com/mapbox' }
```
`$ react-native link react-native-mapbox-navigation-view`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-mapbox-navigation-view` and add `RNMapboxNavigationView.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNMapboxNavigationView.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNMapboxNavigationViewPackage;` to the imports at the top of the file
  - Add `new RNMapboxNavigationViewPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-mapbox-navigation-view'
  	project(':react-native-mapbox-navigation-view').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-mapbox-navigation-view/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-mapbox-navigation-view')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNMapboxNavigationView.sln` in `node_modules/react-native-mapbox-navigation-view/windows/RNMapboxNavigationView.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Mapbox.Navigation.View.RNMapboxNavigationView;` to the usings at the top of the file
  - Add `new RNMapboxNavigationViewPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNMapboxNavigationView, { Mapbox } from 'react-native-mapbox-navigation-view';

// TODO: What to do with the module?
componentDidMount() {
	Mapbox.setAccessToken("pk.eyJ1IjoibWljaGFlbHNsYWJlbnlhayIsImEiOiJjanoxZWhvN3Iwa2t4M2N1b3h2ZHkwNHR1In0.TkL-kezlHSnPdkl0uIQomg")
}

render() {
	return (
		<RNMapboxNavigationView 
			style={{
				width: Dimensions.get('window').width, 
				height: Dimensions.get('window').height
			}}>
	);
}
```
  