luaCoffe.mapping("get/creatingnewclient")
local Luarequest = luacoffe.libs.LuaOkHttp.get('https://api.abacatepay.com/v1/customer/list', 'Bearer abc_dev_zL6eYHcxUZK4XMWzdZkXc0jS')
 return {code=200, response=Luarequest}
