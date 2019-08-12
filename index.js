import { NativeModules } from 'react-native';
import { requireNativeComponent } from 'react-native';

const MapboxNavigationView = requireNativeComponent('MapboxNavigationView');

export const Mapbox = NativeModules.Mapbox;
export default MapboxNavigationView;
