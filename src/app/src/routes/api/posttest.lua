-- Importing a script from package path
luaCoffe.mapping("post/posttest")
local script_test_import = import("script-example")

-- Creating the "class" Script_test
local ScriptTestClass = script_test_import();

--  Making an instance of Script_test
local scriptTestInstance = setmetatable({}, ScriptTestClass);

--  Calling the OBJ method in the instance
local stringRes = scriptTestInstance:Obj();
-- Send this in the requisition body:
--{
--  "params":[
--      {"name": "I'm the best framework"},
--      {"phone": "+5512345678901"},
--      {"password": "plsNeverShowMe"
--  ]
-- }
local user = {
    name = waiting.valueOfParam("name");
    phone = waiting.valueOfParam("phone");
    password = waiting.valueOfParam("password")
}
 obj = nil
if user.password == nil and user.name == nil then
     obj = { code = 400, response = "You should send the params, broh!"}
    return obj;
end
obj = { code = 200, response = stringRes }
return obj;

-- Building a response obj


-- Exibindo o objeto de resposta
