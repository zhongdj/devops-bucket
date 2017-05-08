
### Update master branch from fork repository

```sh
> git checkout master ; git fetch upstream ; git merge upstream/master; git push origin master
```

**Referece:** https://help.github.com/articles/syncing-a-fork/

(Just for the first time) Add the remote (the one you forked from), alias as "upstream":

* for HTTPs

```sh
> git remote add upstream https://{your username}@github.com/{upstream owner}/{upstream project}.git
```

* for SSH

```sh
> git remote add upstream git@github.com:{upstream owner}/{upstream project}.git
```

#### My upstreams

```sh
> git remote add upstream git@github.com:cloudbees/support-kb-articles.git
>
>
```

#### upstream is the alias of the remote repository
```sh
> git remote rm <ALIAS>
```

### Remove all branches but master

Follow this order:

#### 1. Repo
```sh
> git push origin :<branch>
> git branch | grep -v "master" | sed 's/^[ *]*//' | sed 's/^/git push origin :/' | bash
```

#### 2. Locally
**Note**: For checking if the branch has been merged first use "-d"
```sh
> git branch -d <newBranch>
> git branch | grep -v "master" | sed 's/^[ *]*//' | sed 's/^/git branch -D /' | bash
```
### Revert las PR or commit remote and locally

#### to undo a git push ($RemoteBranchName examples: master, feat-1)
```sh
> git push -f origin HEAD^:$RemoteBranchName
```
#### to get to previous commit (preserves working tree)
```sh
> git reset --soft HEAD
```
#### to get back to previous commit (you'll lose working tree)
```sh
> git reset --hard HEAD^
```

#### How can I fetch an unmerged pull request for a branch I don't own? (https://github.com/jenkinsci/bitbucket-branch-source-plugin/pull/21 -> 21)
```sh
> git fetch origin pull/21/head:zd45821
```
### Git Local Configuration

```sh
> git config -l
credential.helper=osxkeychain
filter.lfs.smudge=git-lfs smudge %f
filter.lfs.required=true
filter.lfs.clean=git-lfs clean %f
user.name=Example User 
user.email=example.user@gmail.com
core.excludesfile=/Users/example.user/.gitignore_global
difftool.sourcetree.cmd=opendiff "$LOCAL" "$REMOTE"
difftool.sourcetree.path=
mergetool.sourcetree.cmd=/Applications/SourceTree.app/Contents/Resources/opendiff-w.sh "$LOCAL" "$REMOTE" -ancestor "$BASE" -merge "$MERGED"
mergetool.sourcetree.trustexitcode=true
credential.helper=osxkeychain
core.repositoryformatversion=0
core.filemode=true
core.bare=false
core.logallrefupdates=true
core.ignorecase=true
core.precomposeunicode=true
core.autocrlf=false
core.safecrlf=false
```
Getter

```sh
> git config core.excludesfile
/Users/example.user/.gitignore_global
```

Setter
```
> git config --global user.name "Example User 2"
```
