export interface User{
  userId:string;
  username:string;
  password:string;
  active:boolean;
  appRoles:appRole[]
}

export interface appRole{
  roleId:number;
  roleName:string;
  description:string;

}

