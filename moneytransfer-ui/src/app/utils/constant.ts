import { environment } from '../../environments/environment';

export class Constant {

  public static readonly BASE_SERVER_HOST = environment.baseServerHost;
  public static readonly ENDPOINT = {
    PREPARE_BONIFICO: Constant.BASE_SERVER_HOST + '/private/cliente/:customerId/conto/:accountNumber/bonifico/prepare',
    VERIFY_BONIFICO: Constant.BASE_SERVER_HOST + '/private/cliente/:customerId/conto/:accountNumber/bonifico/verify',
    EXECUTE_BONIFICO: Constant.BASE_SERVER_HOST + '/private/cliente/:customerId/conto/:accountNumber/bonifico/:trxId/execute',
  };
  public static readonly CURRENCY_CODE = 'EUR';

}
