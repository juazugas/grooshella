# grooshella

Console and  shell terminal for the Java web apps.

### Features

With [hawt.io](http://hawt.io) console. Instructions from [hawtio-sample-springboot](https://github.com/hawtio/hawtio/tree/master/hawtio-sample-springboot).

### Deployment

To build within openshift environment :

```sh
# Login into openshift console
oc login <https://127.0.0.1:8443/>

# Select project
oc project my-project

# build project (Optional -Dfabric8.verbose)
mvn clean package fabric8:build 

# launch build and deployment config 
mvn fabric8:resource fabric8:deploy

oc get pods -w
```

### Todo

* Add system storage for scripts.
* Execute from console the saved scripts.
