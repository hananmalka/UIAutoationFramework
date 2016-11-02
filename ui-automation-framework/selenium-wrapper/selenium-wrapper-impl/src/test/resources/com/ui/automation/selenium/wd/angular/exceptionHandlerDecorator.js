angular.module('%s', []).config(['$provide', function($provide) {
  $provide.decorator('$exceptionHandler', ['$delegate',
      function($delegate) {
        return function exceptionServiceDecorator(errorObject) {
          window.automation = errorObject.message;
          $delegate(errorObject);
        }
      }
    ]
  );
}]);