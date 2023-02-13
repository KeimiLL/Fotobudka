module.exports = {
  apps: [
    {
      name: 'fotobudka-api',
      script: 'npm',
      args: 'start',
      log_date_format: 'YYYY-MM-DD HH:mm:ss.SSS',
      watch: false,
      ignore_watch: ['node_modules', 'files'],
      vizion: true,
      env: {
        NODE_ENV: 'development',
      },
      env_production: {
        NODE_ENV: 'production',
      }
    }
  ]
}
