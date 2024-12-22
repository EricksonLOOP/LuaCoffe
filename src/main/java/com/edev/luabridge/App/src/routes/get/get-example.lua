-- Importing a scrit from package path
local script_test_import = import("script-example")

-- Creating the "classe" Script_test
local ScriptTestClass = script_test_import();

--  Making an instance of Script_test
local scriptTestInstance = setmetatable({}, ScriptTestClass);

--  Calling the OBJ method in the instance
local stringRes = scriptTestInstance:Obj();

-- Building a response obj
local obj = { code = 200, response = stringRes }

-- Let's Go!
return obj;