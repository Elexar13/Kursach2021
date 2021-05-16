import {User} from './user';

export class Advertisement {
  constructor(
    public adId?: number,
    public user?: User,
    public userId: number = 0,
    public city?: string,
    public type?: string,
    public adTitle?: string,
    public address?: string,
    public price?: number,
    public countOfRoom?: number,
    public description?: number,
    public filePath?: string,
    public isFavorite: any = null
  ) {}
}
