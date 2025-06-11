function fn() {
    var env = karate.env; // get system property 'karate.env'
    karate.log('karate.env system property was:', env);

    if (!env) {
        env = 'dev';
    }

    var config = {
        env: env,
        baseUrl: 'http://localhost:8080'
    };

    if (env == 'dev') {
        config.baseUrl = 'http://localhost:8080';
    } else if (env == 'test') {
        config.baseUrl = 'http://localhost:8080';
    } else if (env == 'prod') {
        config.baseUrl = 'https://apiintflowservice-prod.apps.your-openshift-cluster.com';
    }

    karate.log('Using baseUrl:', config.baseUrl);
    return config;
}