function fn() {

    var env = karate.env || 'local';
    var config = { env: env };

    karate.log('karate.env system property was:', env);

    if (env === 'test') {
        config.baseUrl = 'http://intflow-service-dev.apps.crc.testing.com:8080';
    } else if (env === 'local') {
        config.baseUrl = 'http://localhost:8080';
    } else {
        karate.log('Unknown env, defaulting to local');
        config.baseUrl = 'http://localhost:8080';
    }

    karate.log('Using baseUrl:', config.baseUrl);
    return config;
}
