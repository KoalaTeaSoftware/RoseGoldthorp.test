# Controller
* This set of features is focusing on design / development decisions.
* So that the code for the common elements of the pages can be written only once, all http page requests are directed to a controller (index.php). This controller will then determine if the request is for a known resource, and deal with it accordingly.