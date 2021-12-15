if [ -d ../services ]; then
    find ../services -type d -d 1 -exec sh -c "cd {} && echo building {} && ./mvnw clean package -D skipTests >/dev/null" \;
else 
    echo "Please run this script in the deploy folder"
fi