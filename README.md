# grooshella

Console and  shell terminal for the Java web apps.

### Features

With [hawt.io](http://hawt.io) console. Instructions from [hawtio-sample-springboot](https://github.com/hawtio/hawtio/tree/master/hawtio-sample-springboot).

### Todo

* Add system storage for scripts.
* Execute from console the saved scripts.

### Deploy in openshift

```sh
oc login https://127.0.0.1:8443
oc whoami -t
docker login -u developer -p <oc_auth_token> 172.30.1.1:5000
docker build -t grooshella/grooshella:0.1.0 -t 172.30.1.1:5000/<project>/grooshella:latest -f paas/Dockerfile .
docker push 172.30.1.1:5000/rhie-prototype/grooshella:latest
oc new-app --docker-image=172.30.1.1:5000/rhie-prototype/grooshella:latest 


