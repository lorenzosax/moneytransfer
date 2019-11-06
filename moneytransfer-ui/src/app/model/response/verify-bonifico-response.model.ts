import {BaseResponse} from './base-response.model';
import {VerifyBonificoInfo} from '../verify-bonifico-info.model';
import {Transaction} from '../transaction.model';

export class VerifyBonificoResponse extends BaseResponse {
  data: VerifyBonificoInfo;
  transaction: Transaction;
}
