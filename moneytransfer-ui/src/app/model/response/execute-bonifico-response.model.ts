import {BaseResponse} from "./base-response.model";
import {ExecuteBonificoInfo} from "../execute-bonifico-info.model";

export class ExecuteBonificoResponse extends BaseResponse{
  data: ExecuteBonificoInfo;
}
