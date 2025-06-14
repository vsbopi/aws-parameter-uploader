<html>
<head><title>AWS Parameter Upload</title></head>
<body>
<h2>Upload Parameter to AWS</h2>
<form action="upload" method="post">
    AWS Access Key: <input type="text" name="accessKey" /><br />
    AWS Secret Key: <input type="password" name="secretKey" /><br />
    Region: <input type="text" name="region" value="us-east-1" /><br />
    Parameter Name: <input type="text" name="paramName" /><br />
    Parameter Value: <input type="text" name="paramValue" /><br />
    Type: 
    <select name="paramType">
        <option value="String">String</option>
        <option value="SecureString">SecureString</option>
    </select><br />
    <input type="submit" value="Upload Parameter" />
</form>
</body>
</html>
