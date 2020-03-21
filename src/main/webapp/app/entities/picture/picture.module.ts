import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RockstockSharedModule } from 'app/shared/shared.module';
import { PictureComponent } from './picture.component';
import { PictureDetailComponent } from './picture-detail.component';
import { PictureUpdateComponent } from './picture-update.component';
import { PictureDeleteDialogComponent } from './picture-delete-dialog.component';
import { pictureRoute } from './picture.route';

@NgModule({
  imports: [RockstockSharedModule, RouterModule.forChild(pictureRoute)],
  declarations: [PictureComponent, PictureDetailComponent, PictureUpdateComponent, PictureDeleteDialogComponent],
  entryComponents: [PictureDeleteDialogComponent]
})
export class RockstockPictureModule {}
