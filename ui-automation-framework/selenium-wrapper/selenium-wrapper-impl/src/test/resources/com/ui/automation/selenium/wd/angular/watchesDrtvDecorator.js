// TODO: DANA - understand what is the role of this module and whether we need something similar in MQM
// currently this throws an exception since it relies on the 'platform-ui' module so this should be an application specific file
// we disabled this module by disabling the 'AngularWatchesDrtvInterceptorModuleProvider'
angular.module('%s', ['platform-ui']).config(['$provide', '$injector', function ($provide, $injector) {
    if($injector.has('perfCountWatchesDirective')){
        $provide.decorator('perfCountWatchesDirective', function($delegate) {
            var directive = $delegate[0];
            directive.template = '';
            directive.templateUrl = '';
            return $delegate;
        });
    }
}]);
