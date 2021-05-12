import {User} from './user';

export class Advertisement {
  constructor(
    public adId?: number,
    public user?: User,
    public city?: string,
    public type?: string,
    public adTitle?: string,
    public price?: number,
    public countOfRoom?: number,
    public description?: number
  ) {}
}
