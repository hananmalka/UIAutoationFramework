'use strict';
/**
 * This file decorates the $browser interface and calls window.setTimeout instead of the standard Angular code
 * since long timeouts are caught by notifyWhenNoOutstandingRequests and fail tests.
 * Since most times there's a legit reason for using a timeout we're just substituting its usage in
 * code with $interval, and doing it for testing sucks...
 * notifyWhenNoOutstandingRequests also fails legit tests on voodoo reasons.
 */
angular.module('%s', []). config(['$provide', function ($provide) {
    $provide.decorator('$browser', function ($delegate) {
        $delegate.defer = function (fn, delay) {
            return window.setTimeout(fn, delay);
        };
        $delegate.defer.cancel = function (deferId) {
            return window.clearTimeout(deferId);
        };
        return $delegate;
    });
}]);
