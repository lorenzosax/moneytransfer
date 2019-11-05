import {BaseResponse} from "./base-response.model";
import {PrepareBonificoInfo} from "../prepare-bonifico-info.model";

export class PrepareBonificoResponse extends BaseResponse{

  data: PrepareBonificoInfo
}
