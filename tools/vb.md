```
vboxmanage --help
```

### Syntax

```
VBoxManage snapshot         |
take  [--description ] [--pause] |
delete | |
restore | |
restorecurrent |
edit ||--current
[--name ]
[--description ] |
list [--details|--machinereadable]
showvminfo |
```

**Note:** UID or name can be used for selecting the VM

### Examples

List available snapshots (Note: * current snapshot)
```
> vboxmanage snapshot AD-contoso.com list
Name: 20160712 (UUID: 67e8772f-c73c-4273-8812-7f1618561827)
   Description:
- AD installed according to http://www.rebeladmin.com/2014/07/step-by-step-guide-to-setup-active-directory-on-windows-server-2012/
- static ip configured
-computer name changed
      Name: 20160726 (UUID: cddfb97c-2506-4fea-a58c-bbcd94c11d16)
      Description:
- Set differect MAC to contoso.com
- Port fowarding to contoso2.com
      Name: 20160728 (UUID: c2f108ad-140e-40c7-98d7-eb372345a242) *
      Description:
- Update to ZD-27695
- Fixed IP
- 192.168.1.81 (DCPR2) included into DNS Manager > Fowarded List
         Name: 20160805 (UUID: 5b5b9ae6-1f84-4089-ae70-3a72426dd843)
         Description:
-Before setting Anonimus User
            Name: 20160805-SSL (UUID: 46cfa701-3ac2-41cf-85dd-7b107d5f5650)
            Description:
-Before trying to secure the instance with SSL
               Name: 20160728 (UUID: e3a67266-814a-4db6-b8ed-31ce720bcc51)
                  Name: 20160728 (UUID: 092431ba-f027-416a-8da3-4451307e01c8)
```

Restoring to 20160712 (UUID: 67e8772f-c73c-4273-8812-7f1618561827)
```
> vboxmanage snapshot AD-contoso.com restore 67e8772f-c73c-4273-8812-7f1618561827
Restoring snapshot 67e8772f-c73c-4273-8812-7f1618561827
0%...10%...20%...30%...40%...50%...60%...70%...80%...90%...100%
```
Deleting 20160728 (UUID: 092431ba-f027-416a-8da3-4451307e01c8)
```
> vboxmanage snapshot AD-contoso.com delete 092431ba-f027-416a-8da3-4451307e01c8
0%...10%...20%...30%...40%...50%...60%...70%...80%...90%...100%
```
Rename a VM
```
> vboxmanage snapshot AD-contoso.com edit 46cfa701-3ac2-41cf-85dd-7b107d5f5650  --name 20161114
```
