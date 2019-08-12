using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Mapbox.Navigation.View.RNMapboxNavigationView
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNMapboxNavigationViewModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNMapboxNavigationViewModule"/>.
        /// </summary>
        internal RNMapboxNavigationViewModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNMapboxNavigationView";
            }
        }
    }
}
