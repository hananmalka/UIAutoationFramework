angular.module('%s', []).config(['$httpProvider', '$provide', function($httpProvider, $provide) {
  $provide.factory('myHttpInterceptor', ['$q', function myInterceptor($q) {
    return {
      request: function(config) {
        config.headers = config.headers || {};
        if (window.correlationId) {
          config.headers['X-CORRELATION_ID_HEADER'] = window.correlationId;
        }
        return config;
      },
      responseError: function(rejection) {
        return $q.reject(rejection);
      }
    }
  }]);
  $httpProvider.interceptors.push('myHttpInterceptor');
}]);
