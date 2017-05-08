# Creating branch/tag

svn copy <RepoSVN/trunk> <RepoSVN/tags/realease-N> -m "comments" # a snapshot of the trunk for any reason
svn copy <RepoSVN/trunk> <RepoSVN/branches/feat-N> -m "comments"
svn copy https://svn.riouxsvn.com/crl_demorepo/trunk  https://svn.riouxsvn.com/crl_demorepo/branches/feat-1 -m "Creating a private branch of feat-1 2"
svn copy https://svn.riouxsvn.com/crl_demorepo/trunk  https://svn.riouxsvn.com/crl_demorepo/branches/feat-1 -m "Creating a private branch of feat-1 2"

# Deleting a branch/tag

svn rm RepoSVN/tags/realease-N> -m "comments"
svn rm <RepoSVN/branches/feat-N> -m "comments"
svn rm https://svn.riouxsvn.com/crl_demorepo/branches/feat-1 -m "removing branch"