# project-sysdes



## IMPORTANT NOTICE

We experienced CORS problems when using the front-end. 

To be able to use the frontend, you must start your browser with security checks disabled, to disable a security policy. 

For chrome, you can execute the following commands: 

### Windows

`"[PATH_TO_CHROME]\chrome.exe" --disable-web-security --disable-gpu --user-data-dir=~/chromeTemp`

### MacOS

`open -n -a /Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --args --user-data-dir="/tmp/chrome_dev_test" --disable-web-security`

### Linux

`google-chrome --disable-web-security -–allow-file-access-from-files`



## Deploying

You can find the script to build all jars in the "deploy" folder, along with the docker-compose.yml file and the kubernetes resource files. 





