function fn() {

    karate.log('karate.env system property was:', env);

    var env = karate.env || 'local';
    var config = { env: env };

    if (env === 'test') {
        config.baseUrl = 'http://intflow-service-latest-dev.apps.crc.testing.com';
    } else if (env === 'local') {
        config.baseUrl = 'http://localhost:8080';
    } else {
        karate.log('Unknown env, defaulting to local');
        config.baseUrl = 'http://localhost:8080';
    }

    karate.log('Using baseUrl:', config.baseUrl);
    return config;
}