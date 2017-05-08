import org.jenkinsci.plugins.github.config.*
import java.net.URL

def apiUrl = "http://api.beescloud.com"
def credentialsId = "github-token"
def manageHooks = true;
def hookUrl = new URL("http://api.beescloud.com:8080/github-webhook/")
def overrideHookUrl = true

def githubServerConfig = new GitHubServerConfig(credentialsId)
githubServerConfig.setApiUrl(apiUrl)
githubServerConfig.setManageHooks(manageHooks)

def servers = [
  githubServerConfig
]

config = new GitHubPluginConfig(servers)
config.setOverrideHookUrl(overrideHookUrl)
config.setHookUrl(hookUrl)
config.save()
